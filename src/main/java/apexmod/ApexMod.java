package apexmod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import characters.Apex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import helpers.ApexImageMaster;
import pathes.AbstractCardEnum;
import pathes.ThmodClassEnum;
import posions.ArrowPotion;
import posions.QicePotion;
// import posions.SchemePotion;
import posions.SheathPotion;
import relics.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date:2022/6/19
 * Author:Vent
 * Description:
 **/
@SpireInitializer
public class ApexMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber{
    private static final String MOD_BADGE = "img/UI_Apex/ApexBadge.png";
    //攻击、技能、能力牌的图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_apex.png";
    private static final String SKILL_CC = "img/512/bg_skill_apex.png";
    private static final String POWER_CC = "img/512/bg_power_apex.png";
    private static final String ENERGY_ORB_CC = "img/512/card_apex_orb.png";
    //攻击、技能、能力牌的图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_apex.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_apex.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_apex.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/card_apex_orb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_Apex/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/apex/apexButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/apex/SelectBg2.png";
    public static final Color DEEPRED = CardHelper.getColor(255, 99, 71);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public ApexMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor(AbstractCardEnum.Apex_COLOR, DEEPRED, DEEPRED, DEEPRED, DEEPRED, DEEPRED, DEEPRED, DEEPRED, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT,POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter((AbstractPlayer)new Apex("Apex"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ThmodClassEnum.Apex_CLASS);
        receiveEditPotions();
    }
    public static void initialize(){
        new ApexMod();
    }
    @Override
    public void receiveEditCards() {
        loadCardsToAdd();
        Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
        while (var1.hasNext()) {
            AbstractCard card = var1.next();
            BaseMod.addCard(card);
        }
    }



    @Override
    public void receivePostExhaust(AbstractCard c) {}

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {}


    @Override
    public void receivePostDungeonInitialize() {}


    @Override
    public void receivePostDraw(AbstractCard arg0) {}

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            BaseMod.addKeyword(new String[] { "奇策" }, "奇策发动时，当前所有手牌减少 [E] ，且抽 #b2 张牌, 获得 [E] ");
            BaseMod.addKeyword(new String[] { "计谋" }, "计谋 是顶尖猎杀者的专属状态，当计谋 #b8 层时将发动 #y奇策 ");
            BaseMod.addKeyword(new String[] { "收刀" }, "下一张攻击牌额外 #b1.35 倍层数伤害,最大为 #b5 层");
            BaseMod.addKeyword(new String[] { "止水" }, "如果当拥有 收刀 状态但是打出攻击牌时不消耗 #y收刀 消耗一层 #y止水 ");
            BaseMod.addKeyword(new String[] { "弓箭" }, "抽 #b1 张牌，选择 #b1 张放置于牌堆顶，如果放置的是 #y弓箭 牌，则下次打出前耗能为 #b0 ");
            BaseMod.addKeyword(new String[] { "阴谋" }, " #y阴谋 是顶尖猎杀者一张牌:抽 #b1 张牌，若为攻击牌，则获得 #b1 点 #y敏捷 ，获得 #b2 层 #y计谋 , NL  #y消耗 , #y保留 ");
            BaseMod.addKeyword(new String[] { "诡计" }, " #y诡计 是顶尖猎杀者一张牌:抽 #b1 张牌，若为技能牌，则获得 #b1 点 #y力量 ，获得 #b2 层 #y计谋 , NL  #y消耗 , #y保留 ");
            BaseMod.addKeyword(new String[] { "标记" }, "标记是一种状态，每当打出 #y点穴 或者 #y略懂 时可以造成对应层数的伤害");
            BaseMod.addKeyword(new String[] { "略懂" }, "略懂是顶尖猎杀者的一张牌");

        }

    }
    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本

        String relic="", card="", power="", potion="", event="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/VMod_Apex_cards-zh.json";
            relic = "localization/VMod_Apex_relics-zh.json";
            power = "localization/VMod_powers.json";
            potion = "localization/VMod_potions.json";
            //event = "localization/ThMod_YM_events-zh.json";
        } else {
            //其他语言配置的JSON
        }

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//     String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    private void loadCardsToAdd() {
        //将自定义的卡牌添加到这里
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Strike_Apex());
        this.cardsToAdd.add(new Defend_Apex());
        this.cardsToAdd.add(new Provoke());
        this.cardsToAdd.add(new Formation());
        this.cardsToAdd.add(new Layout());
        this.cardsToAdd.add(new Shoot());
        this.cardsToAdd.add(new SkillSword());
        this.cardsToAdd.add(new StrengthSword());
        this.cardsToAdd.add(new AFlash());
        this.cardsToAdd.add(new Yangmou());
        this.cardsToAdd.add(new ArrowHit());
        this.cardsToAdd.add(new DefensiveBack());
        this.cardsToAdd.add(new ScabbardHit());
        this.cardsToAdd.add(new CloseCombat());
        this.cardsToAdd.add(new RepairEquipment());
        this.cardsToAdd.add(new Conspiracy());
        this.cardsToAdd.add(new Trickery());
        this.cardsToAdd.add(new StepByStep());
        this.cardsToAdd.add(new InformationWork());
        this.cardsToAdd.add(new BackupDefense());
        this.cardsToAdd.add(new SickWork());
        this.cardsToAdd.add(new KnowLittle());
        this.cardsToAdd.add(new HiddenKnife());
        this.cardsToAdd.add(new ThrowingKnife());
        this.cardsToAdd.add(new Bombard());
        this.cardsToAdd.add(new Coping());
        this.cardsToAdd.add(new Theft());
        this.cardsToAdd.add(new Command());
        this.cardsToAdd.add(new ArrowRain());
        this.cardsToAdd.add(new Brainstorm());
        this.cardsToAdd.add(new Gamble());
        this.cardsToAdd.add(new Seethrough());
        this.cardsToAdd.add(new LoneWolf());
        this.cardsToAdd.add(new DeviseStrategies());
        this.cardsToAdd.add(new JueShengQianLi());
        this.cardsToAdd.add(new LaidoAttack());
        this.cardsToAdd.add(new Battojutsu());
        this.cardsToAdd.add(new AbsoluteForce());
        this.cardsToAdd.add(new FieldDressing());
        this.cardsToAdd.add(new FlashBang());
        this.cardsToAdd.add(new Sinpe());
        this.cardsToAdd.add(new Archedback());
        this.cardsToAdd.add(new GodGuide());
        this.cardsToAdd.add(new OffensiveAttack());
        this.cardsToAdd.add(new LongPlanned());
        this.cardsToAdd.add(new WeaknessEffect());
        this.cardsToAdd.add(new Contend());
        this.cardsToAdd.add(new Chameleon());
        this.cardsToAdd.add(new Ambition());
        this.cardsToAdd.add(new Torture());
        this.cardsToAdd.add(new Almighty());
        this.cardsToAdd.add(new BattoWater());
        this.cardsToAdd.add(new LaidoWink());
        this.cardsToAdd.add(new TooFocus());
        this.cardsToAdd.add(new PlotHit());
        this.cardsToAdd.add(new Purposeful());
        this.cardsToAdd.add(new AmnesiaAgain());
        this.cardsToAdd.add(new BattoSky());
        this.cardsToAdd.add(new MirrorTrick());
        this.cardsToAdd.add(new Skillful());
        this.cardsToAdd.add(new ShootingPractice());
        this.cardsToAdd.add(new SwordMaster());
        this.cardsToAdd.add(new Cardiotonic());
        this.cardsToAdd.add(new InsectKiller());
        this.cardsToAdd.add(new Trump());
        this.cardsToAdd.add(new MindControl());
        this.cardsToAdd.add(new WisdomForm());
        this.cardsToAdd.add(new GodSlay());
        this.cardsToAdd.add(new CraftyPlan());
        this.cardsToAdd.add(new PowerArmor());
        this.cardsToAdd.add(new ExtremePill());
        this.cardsToAdd.add(new IngeniousPlan());
        this.cardsToAdd.add(new SchemeAIntrigue());
        this.cardsToAdd.add(new InterlockingStratagems());
        this.cardsToAdd.add(new KravMaga());
        this.cardsToAdd.add(new StrikeDefend());
        this.cardsToAdd.add(new Accident());


    }



    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool((AbstractRelic)new Checkerboard(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new ChineseChessHorse(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new ChessHorse(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new Yamato(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new FeatherFan(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new SilkBag(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new ChineseCheckerboard(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new PeakedCap(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new CamBow(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new SilverDancer(),AbstractCardEnum.Apex_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic)new Vest(),AbstractCardEnum.Apex_COLOR);
    }




    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        //移除遗物,这里移除了小屋子，太垃圾了。

        // if (AbstractDungeon.player.name == "Apex") {
        //     AbstractDungeon.shopRelicPool.remove("TinyHouse");
        // }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostBattle(AbstractRoom r) {

    }

    @Override
    public void receivePostInitialize() {

        ApexImageMaster.initialize();
    }

    public void receiveEditPotions() {
        BaseMod.addPotion(QicePotion.class, null, null, null, "QicePotion", ThmodClassEnum.Apex_CLASS);
        BaseMod.addPotion(ArrowPotion.class,null ,null ,Color.BROWN ,"ArrowPotion", ThmodClassEnum.Apex_CLASS);
        BaseMod.addPotion(SheathPotion.class,null ,null , null, "SheathPotion", ThmodClassEnum.Apex_CLASS);

    }

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;
    }
}

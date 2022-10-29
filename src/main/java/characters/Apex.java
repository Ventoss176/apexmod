package characters;

import basemod.abstracts.CustomPlayer;
import cards.Provoke;
import cards.Strike_Apex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import helpers.ApexImageMaster;
import pathes.AbstractCardEnum;
import pathes.ThmodClassEnum;

import java.util.ArrayList;

/**
 * Date:2022/6/19
 * Author:Vent
 * Description:
 **/
public class Apex extends CustomPlayer {



    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SELES_SHOULDER_2 = "img/char_Apex/shoulder2.png";
    private static final String SELES_SHOULDER_1 = "img/char_Apex/shoulder1.png";
    private static final String SELES_CORPSE = "img/char_Apex/fallen.png";
    private static final String SELES_STAND = "img/char_Apex/Apex.png";
    //各种素材，不是很懂
    private static final String[] ORB_TEXTURES = new String[] { "img/UI_Apex/EPanel/layer5.png", "img/UI_Apex/EPanel/layer4.png", "img/UI_Apex/EPanel/layer3.png", "img/UI_Apex/EPanel/layer2.png", "img/UI_Apex/EPanel/layer1.png", "img/UI_Apex/EPanel/layer6.png", "img/UI_Apex/EPanel/layer5d.png", "img/UI_Apex/EPanel/layer4d.png", "img/UI_Apex/EPanel/layer3d.png", "img/UI_Apex/EPanel/layer2d.png", "img/UI_Apex/EPanel/layer1d.png" };
    private static final String ORB_VFX = "img/UI_Apex/vfx.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color DEEPRED = CardHelper.getColor(255, 99, 71);

    public Apex(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.Apex_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(SELES_STAND, SELES_SHOULDER_2, SELES_SHOULDER_1, SELES_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
        // this.defaultCode = 1;
        // this.animCode = 0;


    }




    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_Apex");
        retVal.add("Strike_Apex");
        retVal.add("Strike_Apex");
        retVal.add("Strike_Apex");
        retVal.add("Strike_Apex");
        retVal.add("Defend_Apex");
        retVal.add("Defend_Apex");
        retVal.add("Defend_Apex");
        retVal.add("Defend_Apex");
        retVal.add("Defend_Apex");
        retVal.add("Formation");
        retVal.add("Provoke");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startRelics = new ArrayList<>();
        startRelics.add("Checkerboard");
        UnlockTracker.markRelicAsSeen("Checkerboard");
        return startRelics;
    }



    @Override
    public CharSelectInfo getLoadout() {
        //选英雄界面的文字描述
        String title="";
        String flavor="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "顶尖猎杀者";
            flavor = "使用智慧来作战的杀手,寻求真理而来, NL 又或许得到的只是虚无,没有人能够知道答案";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            //当设定为中国台湾省，title和flavor为繁体描述
        } else {
            //其他用英文替代
        }

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,HAND_SIZE , STARTING_GOLD, ASCENSION_MAX_HP_LOSS, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        String title="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "顶尖猎杀者";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "頂尖獵殺者";
        } else {
            title = "Apex Predator";
        }

        return title;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.Apex_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return DEEPRED;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Provoke();

    }

    @Override
    public Color getCardTrailColor() {
        return DEEPRED;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "顶尖猎杀者";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            char_name = "頂尖獵殺者";
        } else {
            char_name = "Apex Predator";
        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new Apex(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "这就是心脏吗？ 你出刀挥舞了好几次, 结果是";
    }

    @Override
    public Color getSlashAttackColor() {
        return DEEPRED;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL };
    }




    @Override
    public String getVampireText() {

        return "我想上次你已经拒绝过我了, NL 所以这次呢?";
    }
    //允许开启终局
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }



}

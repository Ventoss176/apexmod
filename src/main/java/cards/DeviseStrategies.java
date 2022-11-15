package cards;

import basemod.abstracts.CustomCard;
import cards.tempCards.Strategy;
import cards.templates.PowerCardTem;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.ForesightPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantEyeEffect;
import pathes.AbstractCardEnum;
import powers.DeviseStrategiesPower;
import powers.SchemePower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class DeviseStrategies extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DeviseStrategies");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "DeviseStrategies";
    public static final String IMG_PATH = "img/cards_Apex/DeviseStrategies.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public DeviseStrategies() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        // this.baseMagicNumber = 1;
        // this.magicNumber = 1;
        this.cardsToPreview = new Strategy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null) {
            this.addToBot(new VFXAction(new BorderFlashEffect(Color.VIOLET, true)));
            this.addToBot(new VFXAction(new GiantEyeEffect(p.hb.cX, p.hb.cY + 300.0F * Settings.scale, new Color(1.0F, 0.8F, 1.0F, 0.0F))));
        }

        this.addToBot(new ApplyPowerAction(p, p, new DeviseStrategiesPower(p, 10), 10));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SchemePower(p, 3), 3));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new DeviseStrategies();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;

            this.initializeDescription();

            // this.upgradeMagicNumber(1);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }

}

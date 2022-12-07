package cards;

import actions.CostReduction;
import actions.QiceAction;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import cards.tempCards.Fatigued;
import cards.tempCards.Strategy;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import pathes.AbstractCardEnum;
import powers.MindControlPower;
import powers.WisdomFormDebuffPower;
import powers.WisdomFormPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class WisdomForm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WisdomForm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 3;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "WisdomForm";
    public static final String IMG_PATH = "img/cards_Apex/WisdomForm.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WisdomForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = 2;
        this.magicNumber = 2;
//        this.isEthereal = true;
        this.cardsToPreview = new Fatigued();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new DrawPower(p, this.magicNumber), this.magicNumber));
        if(this.upgraded){
//            BaseMod.MAX_HAND_SIZE += 999;
            this.addToBot(new QiceAction());
            this.addToBot(new ApplyPowerAction(p, p, new WisdomFormPower(p, this.magicNumber + 1), this.magicNumber + 1));
        }else {
            this.addToBot(new ApplyPowerAction(p, p, new WisdomFormPower(p, this.magicNumber + 1), this.magicNumber + 1));
        }
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WisdomFormDebuffPower(AbstractDungeon.player, 2), 2));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WisdomForm();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
//            this.isEthereal = false;
            // this.upgradeBaseCost(2);
//            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

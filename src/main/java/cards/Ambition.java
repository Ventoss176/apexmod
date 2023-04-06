package cards;

import basemod.abstracts.CustomCard;
import cards.tempCards.Strategy;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import apexpathes.AbstractCardEnum;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class Ambition extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ambition");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "Ambition";
    public static final String IMG_PATH = "img/cards_Apex/Ambition.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Ambition() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = 2;
        this.magicNumber = 2;
        this.cardsToPreview = new Strategy();
        // this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower("Scheme")){
            if(this.upgraded){
                if(p.getPower("Scheme").amount >= 3){
//                    this.addToBot(new ApplyPowerAction(p, p, new SchemePower(p, this.magicNumber), this.magicNumber));
                    this.addToBot(new DrawCardAction(this.magicNumber));

                }
            }else {
                if(p.getPower("Scheme").amount >= 3){
//                    this.addToBot(new ApplyPowerAction(p, p, new SchemePower(p, this.magicNumber), this.magicNumber));
                    this.addToBot(new DrawCardAction(this.magicNumber));
                }
            }
        }

    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Ambition();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeBaseCost(2);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

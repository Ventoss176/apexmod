package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import pathes.ApexTags;
import powers.SheathPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class BattoSky extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BattoSky");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "BattoSky";
    public static final String IMG_PATH = "img/cards_Apex/BattoSky.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BattoSky() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        // this.tags.add(ApexTags.Batto);
        this.baseMagicNumber = 2;
        this.magicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            while (true){
                AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat();
                if(card.hasTag(ApexTags.Batto)){
                    if (card.cost > 0) {
                        card.cost = 0;
                        card.costForTurn = 0;
                        card.isCostModified = true;
                    }
                    this.addToBot(new MakeTempCardInHandAction(card, 1, true));
                    break;
                }
            }

        }


        // this.addToBot(new DrawBattoToHandAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new SheathPower(p, 1),1));

    }


    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new BattoSky();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            // this.upgradeMagicNumber(1);
            // this.isInnate = true;
            // this.selfRetain = true;
            this.upgradeMagicNumber(1);
            // this.upgradeBaseCost(2);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            // this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

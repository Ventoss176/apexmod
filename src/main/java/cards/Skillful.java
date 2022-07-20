package cards;

import basemod.abstracts.CustomCard;
import cards.templates.SKCardRare;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import pathes.AbstractCardEnum;
import pathes.ApexTags;
import powers.SheathPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class Skillful extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Skillful");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;
    // private static final int BLOCK_AMT = 10;
    // private static final int UPGRADE_PLUS_BLOCK = 5;
    public static final String ID = "Skillful";
    public static final String IMG_PATH = "img/cards_Apex/Skillful.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Skillful() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.tags.add(ApexTags.ARROW);
        // this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = 3;
        this.magicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            while (true){
                AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat();
                if(card.hasTag(ApexTags.ARROW)){
                    if (card.cost > 0) {
                        card.cost = 0;
                        card.costForTurn = 0;
                        card.isCostModified = true;
                    }
                    this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
                    break;
                }
            }

        }


        // this.addToBot(new DrawBattoToHandAction(this.magicNumber));
        // this.addToBot(new ApplyPowerAction(p, p, new SheathPower(p, 1),1));

    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Skillful();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.initializeDescription();
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

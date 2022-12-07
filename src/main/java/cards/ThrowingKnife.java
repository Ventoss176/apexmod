package cards;

import actions.CostReduction;
import basemod.abstracts.CustomCard;
import cards.templates.SkillCardTem;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:飞刀
 **/
public class ThrowingKnife extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ThrowingKnife");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "ThrowingKnife";
    public static final String IMG_PATH = "img/cards_Apex/ThrowingKnife.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public ThrowingKnife() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
         this.baseMagicNumber = 1;
         this.magicNumber = 1;
         this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
        }
        AbstractDungeon.actionManager.addToBottom(new CostReduction(p, 1, false));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new ThrowingKnife();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
//            this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

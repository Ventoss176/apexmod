package cards.tempCards;

import actions.QiceAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

/**
 * @authoer:Ventoss
 * @createDate:2022/11/22
 * @description:
 */
public class Fatigued extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Fatigued");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = -2;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 3;
    public static final String ID = "Fatigued";
    public static final String IMG_PATH = "img/cards_Apex/Fatigued.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Fatigued() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WeakPower(AbstractDungeon.player, 1, true), 1));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player, 1, true), 1));
        }

    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Fatigued();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果

    }
}

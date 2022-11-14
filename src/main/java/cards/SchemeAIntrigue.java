package cards;

import basemod.abstracts.CustomCard;
import cards.tempCards.Trickery;
import cards.templates.PCardRare;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.DevaPower;
import pathes.AbstractCardEnum;
import powers.SchemeAIntriguePower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class SchemeAIntrigue extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SchemeAIntrigue");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "SchemeAIntrigue";
    public static final String IMG_PATH = "img/cards_Apex/SchemeAIntrigue.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public SchemeAIntrigue() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        // this.baseMagicNumber = 1;
        // this.magicNumber = 1;
        this.cardsToPreview = new Trickery();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SchemeAIntriguePower(p,1), 1));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new SchemeAIntrigue();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeMagicNumber(1);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

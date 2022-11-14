package cards;

import actions.BrainstormAction;
import basemod.abstracts.CustomCard;
import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import cards.templates.SkillCardUnCommon;
import com.megacrit.cardcrawl.actions.watcher.CollectAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class Brainstorm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Brainstorm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = -1;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "Brainstorm";
    public static final String IMG_PATH = "img/cards_Apex/Brainstorm.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Brainstorm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        AbstractCard card = new Trickery();
        card.upgrade();
        this.cardsToPreview = card;
        // this.magicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new BrainstormAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded));

    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Brainstorm();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeMagicNumber(1);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

package cards;

import actions.BrainstormAction;
import basemod.abstracts.CustomCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import pathes.AbstractCardEnum;

import java.util.ArrayList;

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
    public AbstractCard card2 = null;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Brainstorm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        AbstractCard card1 = new Trickery();
        this.card2 = new Conspiracy();
        card1.upgrade();
        this.card2.upgrade();
        this.cardsToPreview = card1;
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


//    @Override
//    public void renderCardPreviewInSingleView(SpriteBatch sb) {
//        this.card2.current_x = 1435.0F * Settings.scale;
//        this.card2.current_y = 795.0F * Settings.scale;
//        this.cardsToPreview.current_x = this.card2.current_x + 300.0F * Settings.scale;
//        this.cardsToPreview.current_y = this.card2.current_y;
//        this.card2.drawScale = 0.8F;
//        this.cardsToPreview.drawScale = 0.8F;
//        this.card2.render(sb);
//        this.cardsToPreview.render(sb);
//    }
    @Override
    public void renderCardPreviewInSingleView(SpriteBatch sb) {
        super.renderCardPreviewInSingleView(sb);
        this.card2.current_x = 500.0F * Settings.scale;
        this.card2.current_y = 230.0F * Settings.scale;
        this.card2.drawScale = 0.8F;
        this.card2.render(sb);
    }

    @Override
    public void renderCardPreview(SpriteBatch sb) {
        if (AbstractDungeon.player == null || !AbstractDungeon.player.isDraggingCard) {
            float tmpScale = this.drawScale * 0.8F;
            if (this.current_x > (float)Settings.WIDTH * 0.75F) {
                this.cardsToPreview.current_x = this.current_x + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * this.drawScale;
                this.card2.current_x = this.current_x + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * this.drawScale;
            } else {
                this.cardsToPreview.current_x = this.current_x - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * this.drawScale;
                this.card2.current_x = this.current_x - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * this.drawScale;
            }

            this.cardsToPreview.current_y = this.current_y + (IMG_HEIGHT / 2.0F - IMG_HEIGHT / 2.0F * 0.8F) * this.drawScale;
            this.card2.current_y = this.current_y - (IMG_HEIGHT / 2.0F - IMG_HEIGHT / 2.0F * 0.8F) * this.drawScale * 8.0F;
            this.cardsToPreview.drawScale = tmpScale;
            this.card2.drawScale = tmpScale;
            this.cardsToPreview.render(sb);
            this.card2.render(sb);
        }
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

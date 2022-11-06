package relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class ChineseCheckerboard extends CustomRelic {
    public static final String ID = "ChineseCheckerboard";
    private static final String IMG = "img/relics_Apex/ChineseCheckerboard.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/ChineseCheckerboard.png";

    public ChineseCheckerboard() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), AbstractRelic.RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        this.counter = 0;
    }

    public void obtain() {
        if (AbstractDungeon.player.hasRelic("Checkerboard")) {
            instantObtain(AbstractDungeon.player, 0, false);
        }
        else {
            super.obtain();
        }

    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("Checkerboard");
    }


    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        counter++;
        if(this.counter == 16) {
            AbstractPlayer abstractPlayer = AbstractDungeon.player;
            this.counter = 0;
            flash();
            addToBot((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) abstractPlayer, 1), 1));

            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) abstractPlayer, 1), 1));
            AbstractCard card2 = new Trickery();
            this.addToBot(new MakeTempCardInHandAction(card2));
        }
    }


    @Override
    public void onVictory() {
        if(AbstractDungeon.player.hasRelic("PeakedCap")){
            BaseMod.MAX_HAND_SIZE = 20;
        }else {
            BaseMod.MAX_HAND_SIZE = 10;
        }
        //在胜利时触发
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new ChineseCheckerboard();
    }

}

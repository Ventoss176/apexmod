package relics;

import basemod.abstracts.CustomRelic;
import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class ChineseChessHorse extends CustomRelic {
    public static final String ID = "ChineseChessHorse";
    private static final String IMG = "img/relics_Apex/ChineseChessHorse.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/ChineseChessHorse.png";
    public ChineseChessHorse() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        // this.counter = 0;
        AbstractCard card1 = new Conspiracy();
        // AbstractCard card2 = new Trickery();
        this.addToBot(new MakeTempCardInHandAction(card1, 2, false));
        // this.addToBot(new MakeTempCardInHandAction(card2, 1, false));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}

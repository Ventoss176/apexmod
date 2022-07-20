package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.SchemePower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class FeatherFan extends CustomRelic {
    public static final String ID = "FeatherFan";
    private static final String IMG = "img/relics_Apex/FeatherFan.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/FeatherFan.png";
    public FeatherFan() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, AbstractRelic.LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        counter++;
        if(this.counter == 16) {
            AbstractPlayer abstractPlayer = AbstractDungeon.player;
            this.counter = 0;
            flash();
            this.addToBot(new GainEnergyAction(1));
        }
    }
    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = -1;
    }

}

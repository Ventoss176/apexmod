package relics;

import actions.CostReduction;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.HandSizePower;
import powers.SchemePower;

/**
 * Date:2022/6/23
 * Author:Vent
 * Description:
 **/
public class PeakedCap extends CustomRelic {
    public static final String ID = "PeakedCap";
    private static final String IMG = "img/relics_Apex/PeakedCap.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/PeakedCap.png";
    public PeakedCap() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HandSizePower(AbstractDungeon.player, 10), 10));

    }

    @Override
    public void onEquip() {
        super.onEquip();
        BaseMod.MAX_HAND_SIZE += 4;
    }

    @Override
    public void onUnequip() {
        super.onUnequip();
        BaseMod.MAX_HAND_SIZE -= 4;
    }

    //    @Override
//    public void onShuffle() {
//        this.flash();
//        // this.addToBot(new GainEnergyAction(1));
//        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new DrawCardAction(3));
//        // this.addToTop(new CostReduction(AbstractDungeon.player, 99, true));
//    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



}

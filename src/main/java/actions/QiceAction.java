package actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import powers.HandSizePower;

/**
 * @authoer:Ventoss
 * @createDate:2022/11/4
 * @description:
 */
public class QiceAction extends AbstractGameAction {
    private float startingDuration;
//    public static int HAND_SIZE_ADD = 0;
    // private boolean isUpgraded;

    public QiceAction() {
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.play("STANCE_ENTER_CALM");
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new DrawCardAction(2));
            this.addToTop(new CostReduction(AbstractDungeon.player, 99, true));
//            HAND_SIZE_ADD += 2;
//            if(AbstractDungeon.player.hasRelic("Checkerboard") ||
//                    AbstractDungeon.player.hasRelic("ChineseCheckerboard")){
//                this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HandSizePower(AbstractDungeon.player, 2), 2));
//                BaseMod.MAX_HAND_SIZE += 2;
//
//            }
        }
        this.isDone = true;


    }
}

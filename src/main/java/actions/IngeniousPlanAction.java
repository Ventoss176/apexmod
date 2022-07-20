package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class IngeniousPlanAction extends AbstractGameAction {
    private float startingDuration;
    // private boolean isUpgraded;

    public IngeniousPlanAction() {
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            int count = AbstractDungeon.player.hand.size();
            if (count != 0) {
                // this.addToTop(new DrawCardAction(this.target, count));
                this.addToTop(new DiscardAction(this.target, this.target, count, true));
            }

            this.isDone = true;
        }

    }
}

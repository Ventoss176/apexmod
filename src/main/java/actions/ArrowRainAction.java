package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class ArrowRainAction extends AbstractGameAction {
    public ArrowRainAction(AbstractCreature source) {
        this.source = source;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Iterator var1 = AbstractDungeon.player.hand.group.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (c.type == AbstractCard.CardType.ATTACK) {
                    this.addToTop(new DiscardSpecificCardAction(c));
                }
            }

            this.isDone = true;
        }

    }
}

package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:观星
 **/
public class StargazingAction extends AbstractGameAction {
    private float startingDuration;

    public StargazingAction(int numCards) {
        this.amount = numCards;


        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            Iterator var1;
            AbstractCard c;
            if (this.duration == this.startingDuration) {
                var1 = AbstractDungeon.player.powers.iterator();

                while(var1.hasNext()) {
                    AbstractPower p = (AbstractPower)var1.next();
                    // p.onScry();
                }

                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                if (this.amount != -1) {
                    for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                        tmpGroup.addToTop((AbstractCard) AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
                    }
                }
                // } else {
                //     Iterator var5 = AbstractDungeon.player.drawPile.group.iterator();
                //
                //     while(var5.hasNext()) {
                //         c = (AbstractCard)var5.next();
                //         tmpGroup.addToBottom(c);
                //     }
                // }

                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, "观看牌顶");
            }
            // else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            //     var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                // while(var1.hasNext()) {
                //     c = (AbstractCard)var1.next();
                //     AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                // }

                // AbstractDungeon.gridSelectScreen.selectedCards.clear();
            // }

            // var1 = AbstractDungeon.player.discardPile.group.iterator();

            // while(var1.hasNext()) {
            //     c = (AbstractCard)var1.next();
            //     c.triggerOnScry();
            // }

            this.tickDuration();
        }
    }
}

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

                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, "观看牌顶");
                } else if (Settings.language == Settings.GameLanguage.ENG) {
                    AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, "The top cards of your draw pile.");
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, "The top cards of your draw pile.");
                }
            }

            this.tickDuration();
        }
    }
}

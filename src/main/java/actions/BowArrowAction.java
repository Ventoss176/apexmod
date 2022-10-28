package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import pathes.ApexTags;

import java.util.Iterator;

/**
 * @authoer:Ventoss
 * @createDate:2022/10/28
 * @description:
 */
public class BowArrowAction extends AbstractGameAction {

    private AbstractPlayer p;
//    private static final UIStrings uiStrings;
//    public static final String[] TEXT;

    public BowArrowAction() {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard();
                if (c.cost > 0) {
                    if(c.hasTag(ApexTags.ARROW) || AbstractDungeon.player.hasRelic("CamBow")){
                        c.freeToPlayOnce = true;
                    }
                }

                this.p.hand.moveToDeck(c, false);
                AbstractDungeon.player.hand.refreshHandLayout();
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open("选择一张牌", 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToDeck(c, false)) {
                    c = (AbstractCard)var1.next();
                    if (c.cost > 0) {
                        if(c.hasTag(ApexTags.ARROW) || AbstractDungeon.player.hasRelic("CamBow")){
                            c.freeToPlayOnce = true;
                        }

                    }
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}

package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class DaoistMagicAction extends AbstractGameAction {
    public DaoistMagicAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.upgradeAllCardsInGroup(p.hand);
            this.upgradeAllCardsInGroup(p.drawPile);
            this.upgradeAllCardsInGroup(p.discardPile);
            this.upgradeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }

    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        Iterator var2 = cardGroup.group.iterator();

        while(var2.hasNext()) {
            System.out.println("Start loop");
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade() && (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE) || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND))) {
                System.out.println("start upgrade");
                c.upgrade();
                c.applyPowers();

            }
        }

    }
}

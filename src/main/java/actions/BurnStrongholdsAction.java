package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import powers.SchemePower;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @authoer:Ventoss
 * @createDate:2022/11/3
 * @description:
 */
public class BurnStrongholdsAction extends AbstractGameAction {

    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.type != AbstractCard.CardType.SKILL || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
                cardsToExhaust.add(c);
            }
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SchemePower(AbstractDungeon.player, 1), 1));
//            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
//            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1), 1));
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

        this.isDone = true;
    }

}

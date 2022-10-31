package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import powers.SchemePower;

import java.util.Iterator;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class TrickeryAction extends AbstractGameAction{
    private int mNumber;

    public TrickeryAction(int mNumber) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.mNumber = mNumber;
        System.out.println("Constctor： " + this.mNumber);
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == AbstractCard.CardType.SKILL) {
                System.out.println(this.mNumber);
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, this.mNumber - 1), this.mNumber - 1));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new SchemePower((AbstractCreature) AbstractDungeon.player, this.mNumber), this.mNumber));
                break;
            }
        }

        this.isDone = true;
    }
}

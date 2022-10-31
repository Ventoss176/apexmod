package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import powers.SchemePower;

import java.util.Iterator;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class ConspiracyAction extends AbstractGameAction {
    private int mNumber;

    public ConspiracyAction(int mNumber) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.mNumber = mNumber;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                System.out.println(this.mNumber);
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, this.mNumber - 1), this.mNumber - 1));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new SchemePower((AbstractCreature) AbstractDungeon.player, this.mNumber), this.mNumber));
                break;
            }
        }

        this.isDone = true;
    }
}

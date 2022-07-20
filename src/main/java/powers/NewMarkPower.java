package powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/7/17
 * Author:Vent
 * Description:
 **/
public class NewMarkPower extends AbstractPower {
    public static final String POWER_ID = "NewMarkPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NewMarkPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = "NewMarkPower";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("pressure_points");
        this.type = PowerType.DEBUFF;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    public void triggerMarks(AbstractCard card) {
        if (card.cardID.equals("PathToVictory") || card.cardID.equals("KnowLittle")) {
            this.addToBot(new LoseHPAction(this.owner, (AbstractCreature)null, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }

    }
}

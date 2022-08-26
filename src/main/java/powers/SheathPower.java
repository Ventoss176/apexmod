package powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class SheathPower extends AbstractPower {
    public static final String POWER_ID = "Sheath";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SheathPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Sheath";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("vigor");
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * (float)Math.pow(1.35,(double)this.amount)  : damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Sheath"));
        }

    }
}

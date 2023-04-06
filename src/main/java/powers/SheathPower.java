package powers;

import actions.CostReduction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

    private static int LIMITED = 5;

    public SheathPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Sheath";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("vigor");
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        if(AbstractDungeon.player.hasPower("SheathLimitPower")){
            LIMITED = 5 + AbstractDungeon.player.getPower("SheathLimitPower").amount;
        }
        if (this.amount >= LIMITED) {
            this.amount = LIMITED;
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if(AbstractDungeon.player.hasPower("SheathLimitPower")){
            LIMITED = 5 + AbstractDungeon.player.getPower("SheathLimitPower").amount;
        }

        if (this.amount >= LIMITED) {
            this.amount = LIMITED;
        }



    }

    public void updateDescription() {
        if(AbstractDungeon.player.hasPower("SheathLimitPower")){
            LIMITED = 5 + AbstractDungeon.player.getPower("SheathLimitPower").amount;
        }
        if(AbstractDungeon.player.hasRelic("Yamato")){
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + LIMITED;
        }else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + LIMITED;
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if(AbstractDungeon.player.hasRelic("Yamato")){
            return type == DamageInfo.DamageType.NORMAL ? damage * (float)Math.pow(1.5,(double)this.amount)  : damage;
        }else {
            return type == DamageInfo.DamageType.NORMAL ? damage * (float)Math.pow(1.35,(double)this.amount)  : damage;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && AbstractDungeon.player.hasPower("HeartWater")) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new HeartWaterPower(this.owner, -1), -1));
        }else if (card.type == AbstractCard.CardType.ATTACK ) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Sheath"));
        }

    }
}

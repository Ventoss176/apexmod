package powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/**
 * @authoer:Ventoss
 * @createDate:2022/11/2
 * @description:
 */
public class WisdomFormDebuffPower extends AbstractPower {
    public static final String POWER_ID = "WisdomFormDebuff";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WisdomFormDebuffPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "WisdomFormDebuff";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("wraithForm");
        this.canGoNegative = true;
        this.type = PowerType.DEBUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {

        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
    }


}

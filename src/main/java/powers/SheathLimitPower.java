package powers;

import cards.tempCards.Fatigued;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @authoer:Ventoss
 * @createDate:2023/2/9
 * @description:
 */
public class SheathLimitPower extends AbstractPower{
    public static final String POWER_ID = "SheathLimitPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SheathLimitPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "SheathLimitPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("attackBurn");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onVictory() {

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (Settings.language == Settings.GameLanguage.ZHS) {

            this.description = DESCRIPTIONS[0] +this.amount + DESCRIPTIONS[1];
        } else {

            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

        }

    }


}

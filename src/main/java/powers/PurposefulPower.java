package powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class PurposefulPower extends AbstractPower {
    public static final String POWER_ID = "Purposeful";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PurposefulPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = "Purposeful";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("tools");
    }

    public void updateDescription() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];

        } else {
            if (this.amount == 1){
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }else {
                this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5];

            }
        }
    }



    @Override
    public void atStartOfTurnPostDraw() {
        this.addToBot(new DrawCardAction(this.owner, this.amount));
        this.addToBot(new PutOnDeckAction(this.owner, this.owner, this.amount, false));

    }


}

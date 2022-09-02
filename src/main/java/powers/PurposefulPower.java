package powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.ApexTags;

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
        this.loadRegion("armor");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    // @Override
    // public void atEndOfTurn(boolean isPlayer) {
    //     if(AbstractDungeon.player.hasPower("Scheme") &&  AbstractDungeon.player.getPower("Scheme").amount > 0 ){
    //         int scheme = AbstractDungeon.player.getPower("Scheme").amount;
    //         this.amount *= scheme;
    //         this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
    //     }
    //
    // }

    @Override
    public void atStartOfTurn() {
        if(AbstractDungeon.player.hasPower("Scheme") &&  AbstractDungeon.player.getPower("Scheme").amount > 0 ){
            int scheme = AbstractDungeon.player.getPower("Scheme").amount;
            if(scheme > 0){

                scheme *= this.amount ;
                this.addToBot(new GainBlockAction(this.owner, this.owner, scheme));
            }else{
                this.addToBot(new GainBlockAction(this.owner, this.owner, 0));
            }

        }
    }
}

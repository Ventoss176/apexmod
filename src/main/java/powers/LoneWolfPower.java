package powers;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:不能打出攻击牌
 **/
public class LoneWolfPower extends AbstractPower{
    public static final String POWER_ID = "LoneWolf";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoneWolfPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "LoneWolf";
        this.owner = owner;
        this.amount = 1;
        this.updateDescription();
        this.loadRegion("entangle");
        this.isTurnBased = true;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.updateAttacksCost();


    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_ENTANGLED", 0.05F);
    }

    // @Override
    // public boolean canPlayCard(AbstractCard card) {
    //     if(card.type == AbstractCard.CardType.ATTACK){
    //         card.costForTurn = -2;
    //         return false;
    //
    //     }else {
    //         return true;
    //     }
    // }

    public void updateAttacksCost(){
        CardGroup attacks = AbstractDungeon.player.hand.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            attacks.group.get(i).costForTurn = 999;

        }


    }

    @Override
    public void atStartOfTurn() {
        CardGroup attacks = AbstractDungeon.player.hand.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            attacks.group.get(i).costForTurn = 999;

        }


    }

    @Override
    public void onCardDraw(AbstractCard card) {
        CardGroup attacks = AbstractDungeon.player.hand.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            attacks.group.get(i).costForTurn = 999;

        }
    }



    // @Override
    // public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
    //     CardGroup attacks = AbstractDungeon.player.hand.getAttacks();
    //     for (int i = 0; i < attacks.size(); i++) {
    //         attacks.getNCardFromTop(i).cantUseMessage = "cant' ";
    //     }
    // }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    // public void atEndOfTurn(boolean isPlayer) {
    //
    // }
}

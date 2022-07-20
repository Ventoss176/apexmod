package powers;

import actions.StargazingAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:运筹帷幄
 **/
public class DeviseStrategiesPower extends AbstractPower {

    public static final String POWER_ID = "DeviseStrategies";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;



    public DeviseStrategiesPower(AbstractCreature owner, int scryAmt) {
        this.name = powerStrings.NAME;
        this.ID = "DeviseStrategies";
        this.owner = owner;
        this.amount = scryAmt;
        this.updateDescription();
        this.loadRegion("wireheading");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.player.drawPile.size() <= 0) {
            this.addToTop(new EmptyDeckShuffleAction());
        }

        this.flash();
        this.addToBot(new StargazingAction(this.amount));
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        // this.addToBot(new GainBlockAction(AbstractDungeon.player, this.amount - (9 * this.amount)));
        super.onCardDraw(card);
    }
}

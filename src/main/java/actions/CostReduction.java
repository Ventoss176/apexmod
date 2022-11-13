package actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:减费power，包含随机减费,固定减费
 **/

public class CostReduction extends AbstractGameAction {
    // private static final UIStrings uiStrings;
    // public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    public static int numCostReduce;
    public static float DURATION;
//    public static final Logger logger = LogManager.getLogger(CostReduction.class);

    public CostReduction(AbstractCreature target, int amount, boolean isRandom) {
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if(this.p.hand.size() <= this.amount){
//                logger.info("hand.size <= 1");
                // System.out.println("hand.size <= 1");
                Iterator var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
//                    logger.info("All card cost -1");
//                    System.out.println("All card cost -1");
                    AbstractCard c = (AbstractCard)var1.next();
                    if (c.costForTurn > 0) {
                        c.costForTurn = c.costForTurn - 1;
                        c.isCostModifiedForTurn = true;
                    }

                }
                this.tickDuration();
                return;
            }


            if(!isRandom){
//                 logger.info("No Random Cost -1");
                // System.out.println("No Random card cost -1");
                numCostReduce = this.amount;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.handCardSelectScreen.open("减少耗能", this.amount, false, true);
                } else if (Settings.language == Settings.GameLanguage.ENG) {
                    AbstractDungeon.handCardSelectScreen.open("to reduce Cost", this.amount, false, true);
                } else {
                    AbstractDungeon.handCardSelectScreen.open("to reduce Cost", this.amount, false, true);
                }
                this.addToBot(new WaitAction(0.25F));
                this.tickDuration();
                return;
            }

            boolean betterPossible = false;
            Iterator var3 = this.p.hand.group.iterator();

            while(var3.hasNext()) {
                AbstractCard c = (AbstractCard)var3.next();
                if (c.costForTurn > 0) {
                    betterPossible = true;
                }
            }

            if (betterPossible) {
                this.findAndModifyCard(betterPossible);
            }



            // int i = this.p.hand.size();

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
//            logger.info("Select Card Cost -1");
            // System.out.println("Select card cost -1");
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var4.hasNext()) {
                // System.out.println("Select card enable");

                AbstractCard c = (AbstractCard)var4.next();
                c.setCostForTurn(c.costForTurn - 1);
                c.isCostModifiedForTurn = true;
                this.p.hand.addToTop(c);
            }


            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            // AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }

        this.tickDuration();
    }



    private void findAndModifyCard(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (better) {
            if (c.costForTurn > 0) {
                c.costForTurn = c.costForTurn -1;
                c.isCostModifiedForTurn = true;
                c.superFlash(Color.GOLD.cpy());
                return;
            } else {
                this.findAndModifyCard(better);
            }
        }else if (c.cost > 0) {
            c.costForTurn = c.costForTurn -1;
            c.isCostModified = true;
            c.superFlash(Color.GOLD.cpy());
        } else {
            this.findAndModifyCard(better);
        }

    }

    static {
        // uiStrings = CardCrawlGame.languagePack.getUIString("CostReduction");
        // TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}

package powers;

import cards.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import apexpathes.ApexTags;

import java.util.Iterator;

/**
 * Date:2022/6/23
 * Author:Vent
 * Description:
 **/
public class ShootingPracticePower extends AbstractPower {
    public static final String POWER_ID = "ShootingPractice";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShootingPracticePower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = "ShootingPractice";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("accuracy");
        this.updateExistingArrow();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.updateExistingArrow();
    }

    private void updateExistingArrow() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof ArrowHit) {
                if (!c.upgraded) {
                    c.baseDamage = 7 + this.amount;
                } else {
                    c.baseDamage = 9 + this.amount;
                }
            }

            if (c instanceof ArrowRain) {
                if (!c.upgraded) {
                    c.baseDamage = 7 + this.amount;
                } else {
                    c.baseDamage = 10 + this.amount;
                }
            }

            if (c instanceof Archedback) {
                if (!c.upgraded) {
                    c.baseDamage = 9 + this.amount;
                } else {
                    c.baseDamage = 13 + this.amount;
                }
            }

            if (c instanceof Sinpe) {
                if (!c.upgraded) {
                    c.baseDamage = 40 + this.amount;
                } else {
                    c.baseDamage = 50 + this.amount;
                }
            }

            if (c instanceof PlotHit) {
                if (!c.upgraded) {
                    c.baseDamage = 9 + this.amount;
                } else {
                    c.baseDamage = 12 + this.amount;
                }
            }

            if (c instanceof MirrorTrick) {
                if (!c.upgraded) {
                    c.baseDamage = 16 + this.amount;
                } else {
                    c.baseDamage = 20 + this.amount;
                }
            }



        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.hasTag(ApexTags.ARROW)) {
                // c = (AbstractCard)var1.next();
                if (c instanceof ArrowHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 9 + this.amount;
                    }
                }

                if (c instanceof ArrowRain) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 10 + this.amount;
                    }
                }

                if (c instanceof Archedback) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 13 + this.amount;
                    }
                }

                if (c instanceof Sinpe) {
                    if (!c.upgraded) {
                        c.baseDamage = 40 + this.amount;
                    } else {
                        c.baseDamage = 50 + this.amount;
                    }
                }

                if (c instanceof PlotHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 12 + this.amount;
                    }
                }

                if (c instanceof MirrorTrick) {
                    if (!c.upgraded) {
                        c.baseDamage = 16 + this.amount;
                    } else {
                        c.baseDamage = 20 + this.amount;
                    }
                }

            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.hasTag(ApexTags.ARROW)) {
                // c = (AbstractCard)var1.next();
                if (c instanceof ArrowHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 9 + this.amount;
                    }
                }

                if (c instanceof ArrowRain) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 10 + this.amount;
                    }
                }

                if (c instanceof Archedback) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 13 + this.amount;
                    }
                }

                if (c instanceof Sinpe) {
                    if (!c.upgraded) {
                        c.baseDamage = 40 + this.amount;
                    } else {
                        c.baseDamage = 50 + this.amount;
                    }
                }

                if (c instanceof PlotHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 12 + this.amount;
                    }
                }

                if (c instanceof MirrorTrick) {
                    if (!c.upgraded) {
                        c.baseDamage = 16 + this.amount;
                    } else {
                        c.baseDamage = 20 + this.amount;
                    }
                }

            }
        }

        var1 = AbstractDungeon.player.exhaustPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.hasTag(ApexTags.ARROW)) {
                // c = (AbstractCard)var1.next();
                if (c instanceof ArrowHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 9 + this.amount;
                    }
                }

                if (c instanceof ArrowRain) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 10 + this.amount;
                    }
                }

                if (c instanceof Archedback) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 13 + this.amount;
                    }
                }

                if (c instanceof Sinpe) {
                    if (!c.upgraded) {
                        c.baseDamage = 40 + this.amount;
                    } else {
                        c.baseDamage = 50 + this.amount;
                    }
                }

                if (c instanceof PlotHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 12 + this.amount;
                    }
                }

                if (c instanceof MirrorTrick) {
                    if (!c.upgraded) {
                        c.baseDamage = 16 + this.amount;
                    } else {
                        c.baseDamage = 20 + this.amount;
                    }
                }
            }
        }

    }

    public void onDrawOrDiscard() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.hasTag(ApexTags.ARROW)) {
                // c = (AbstractCard)var1.next();
                if (c instanceof ArrowHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 9 + this.amount;
                    }
                }

                if (c instanceof ArrowRain) {
                    if (!c.upgraded) {
                        c.baseDamage = 7 + this.amount;
                    } else {
                        c.baseDamage = 10 + this.amount;
                    }
                }

                if (c instanceof Archedback) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 13 + this.amount;
                    }
                }

                if (c instanceof Sinpe) {
                    if (!c.upgraded) {
                        c.baseDamage = 40 + this.amount;
                    } else {
                        c.baseDamage = 50 + this.amount;
                    }
                }

                if (c instanceof PlotHit) {
                    if (!c.upgraded) {
                        c.baseDamage = 9 + this.amount;
                    } else {
                        c.baseDamage = 12 + this.amount;
                    }
                }
                if (c instanceof MirrorTrick) {
                    if (!c.upgraded) {
                        c.baseDamage = 16 + this.amount;
                    } else {
                        c.baseDamage = 20 + this.amount;
                    }
                }
            }
        }

    }
}

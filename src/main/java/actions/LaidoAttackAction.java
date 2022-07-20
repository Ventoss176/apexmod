package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import powers.SheathPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class LaidoAttackAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private AbstractPlayer p;
    private AbstractMonster m;

    public LaidoAttackAction(AbstractMonster target, AbstractPlayer p,DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.m = target;
        this.p = p;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.SLASH_VERTICAL;
        this.duration = 0.01F;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
        } else {
            if (this.p.hasPower("Sheath")) {
                if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
                    if (this.info.type != DamageInfo.DamageType.THORNS && this.info.owner.isDying) {
                        this.isDone = true;
                        return;
                    }

                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                }

                this.tickDuration();
                if (this.isDone && this.target != null && this.target.currentHealth > 0) {
                    this.target.damage(this.info);
                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }

                    this.addToTop(new WaitAction(0.1F));
                }
            } else {
                this.addToTop(new ApplyPowerAction(p, p, new SheathPower(p, 1),1));
                this.isDone = true;
            }

        }
    }
}

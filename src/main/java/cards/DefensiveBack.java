package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import apexpathes.AbstractCardEnum;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class DefensiveBack extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DefensiveBack");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/DefensiveBack.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 4;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS = 2;
    public static final String ID = "DefensiveBack";
    public DefensiveBack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseBlock = BLOCK;
        this.baseDamage = ATTACK_DMG;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new WaitAction(0.1F));
        if (p != null && m != null) {
            this.addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        }


        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new DefensiveBack();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS);
            this.upgradeBlock(UPGRADE_PLUS);
            this.initializeDescription();
        }

    }

}

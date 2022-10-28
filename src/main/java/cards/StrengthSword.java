package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import pathes.AbstractCardEnum;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:
 **/
public class StrengthSword extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("StrengthSword");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/StrengthSword.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "StrengthSword";

    public StrengthSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        for (int i = 0; i < this.magicNumber; ++i) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        }
        this.addToBot(new ApplyPowerAction(p, p, new NoDrawPower(p), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard) new StrengthSword();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            this.upgradeName();
            // this.upgradeMagicNumber(1);
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.initializeDescription();
        }

    }
}

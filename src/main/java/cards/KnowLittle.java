package cards;

import basemod.abstracts.CustomCard;
import cards.templates.SkillCardTem;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.vfx.combat.PressurePointEffect;
import pathes.AbstractCardEnum;
import powers.NewMarkPower;
import powers.SchemePower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:卡牌 ： 略懂
 **/
public class KnowLittle extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KnowLittle");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    // private static final int BLOCK_AMT = 6;
    // private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "KnowLittle";
    public static final String IMG_PATH = "img/cards_Apex/KnowLittle.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public KnowLittle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        // this.baseMagicNumber = 1;
        // this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.upgraded){
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, 2), 2, AbstractGameAction.AttackEffect.POISON));
            if (m != null) {
                this.addToBot(new VFXAction(new PressurePointEffect(m.hb.cX, m.hb.cY)));
            }

            this.addToBot(new ApplyPowerAction(m, p, new NewMarkPower(m, 4), 4));
            this.addToBot(new TriggerMarksAction(this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SchemePower(p, 1), 1));
        }else {
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, 3), 3, AbstractGameAction.AttackEffect.POISON));
            if (m != null) {
                this.addToBot(new VFXAction(new PressurePointEffect(m.hb.cX, m.hb.cY)));
            }

            this.addToBot(new ApplyPowerAction(m, p, new NewMarkPower(m, 6), 6));
            this.addToBot(new TriggerMarksAction(this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SchemePower(p, 2), 2));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new KnowLittle();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            this.initializeDescription();
            // this.upgradeMagicNumber(1);
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}

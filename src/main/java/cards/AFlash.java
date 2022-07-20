package cards;

import basemod.abstracts.CustomCard;
import cards.StrengthSword;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import pathes.AbstractCardEnum;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:一闪卡片，获得2点临时敏捷
 **/
public class AFlash extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AFlash");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/AFlash.png";
    private static final int COST = 0;
    // private static final int ATTACK_DMG = 9;
    // private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "AFlash";

    public AFlash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.COMMON, CardTarget.SELF);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高两点临时敏捷
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.initializeDescription();
            // upgradeDamage(UPGRADE_PLUS_DMG);
        }


    }

    @Override
    public AbstractCard makeCopy() {
        return new AFlash();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) abstractPlayer, this.magicNumber), this.magicNumber));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new LoseDexterityPower((AbstractCreature) abstractPlayer, this.magicNumber), this.magicNumber));
    }
}

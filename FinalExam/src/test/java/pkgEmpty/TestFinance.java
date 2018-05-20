package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

import org.apache.poi.ss.formula.functions.*;

public class TestFinance {

	@Test
	public void TestPV() {

		int iYearsToWork = 40;
		double dAnnualReturnWorking = 0.07;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;

		double PV = Retirement.PV(dAnnualReturnRetired / 12, iYearsRetired * 12, dRequiredIncome - dMonthlySSI, 0, false);
		
		int iYearsToWork2 = 35;
		double dAnnualReturnWorking2 = 0.06;
		int iYearsRetired2 = 15;
		double dAnnualReturnRetired2 = 0.03;
		double dRequiredIncome2 = 11251;
		double dMonthlySSI2 = 3145;

		double PV2 = Retirement.PV(dAnnualReturnRetired2 / 12, iYearsRetired2 * 12, dRequiredIncome2 - dMonthlySSI2, 0, false);
		
		//	In my calculations, in order to receive a payment of $7358 ($10000-2642), if you were making 2% on your return, and you wanted it paid off
		//	over a 20 year period... You'd need to save $1,454,485.55.
		
		//	Accounting rules- PV returns a negative number if you pass in a positive PMT amount.  Take the absolute value
		
		//	I want to compare a double with a double... Doubles are not precise... I have to give a rounding factor.
		//	Note the third argument.  That says only compare the double values to the hundredth place.
		
		//Test 1
		assertEquals(1454485.55, Math.abs(PV), 0.01);
		
		//Test 2
		assertEquals(1173793.15, Math.abs(PV2), 0.01);
		
	}

	@Test
	public void TestPMT() {
		
		int iYearsToWork = 40;
		double dAnnualReturnWorking = 0.07;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		
		double PV = Retirement.PV(dAnnualReturnRetired / 12, iYearsRetired * 12, dRequiredIncome - dMonthlySSI, 0, false);
		double PMT = Retirement.PMT(dAnnualReturnWorking / 12, iYearsToWork * 12, 0, PV, false);
		
		int iYearsToWork2 = 35;
		double dAnnualReturnWorking2 = 0.06;
		int iYearsRetired2 = 15;
		double dAnnualReturnRetired2 = 0.03;
		double dRequiredIncome2 = 11251;
		double dMonthlySSI2 = 3145;

		double PV2 = Retirement.PV(dAnnualReturnRetired2 / 12, iYearsRetired2 * 12, dRequiredIncome2 - dMonthlySSI2, 0, false);
		double PMT2 = Retirement.PMT(dAnnualReturnWorking2 / 12, iYearsToWork2 * 12, 0, PV2, false);
		
		//Test 1
		assertEquals(554.13, Math.abs(PMT), 0.01);
		
		//Test 2
		assertEquals(823.88, Math.abs(PMT2), 0.01);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_prg_03_14_internet_service_provider_part_2;

import javax.swing.JOptionPane;
/**
 *
 * @author bluebackdev
 * Modify the program that you wrote for Programming Challenge 13 so it also
 * calculates and displays the amount of money Package A customers would save
 * if they purchased Package B or C, and the amount of money that Package B
 * customers would save if they purchased Package C. If there would be no
 * savings, no message should be printed.
 */
public class JAVA_PRG_03_14_Internet_Service_Provider_Part_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // String to hold user inputs and formatted values
        String strUserPackage;
        String strUserHours;
        String strFormattedOutput;
        
        // Declare constant floats for package rates
        // and data overage charges
        final float FLT_PACKAGE_CHARGE_A = 9.95f;
        final float FLT_PACKAGE_OVERAGE_A = 2.0f;
        final float FLT_PACKAGE_HOURS_A = 10.0f;
        final float FLT_PACKAGE_CHARGE_B = 13.95f;
        final float FLT_PACKAGE_OVERAGE_B = 1.0f;
        final float FLT_PACKAGE_HOURS_B = 20.0f;
        final float FLT_PACKAGE_CHARGE_C = 19.95f;
                
        
        // Declare float to hold converted user input
        // for hours and calculated cost, initialize
        // fltCalculatedTotal to avoid squiggly lines
        // when formatting output message at end of program
        float fltUserHours;
        float fltCalculatedTotal = 0.0f;
        float fltCalculatedTotalA = 0.0f;
        float fltCalculatedTotalB = 0.0f;
        float fltCalculatedTotalC = 0.0f;
        float fltSavings1 = 0.0f; // New variables to hold difference between
        float fltSavings2 = 0.0f; // all plans total costs for comparison
        
        // Get package and hours from user
        strUserPackage = JOptionPane.showInputDialog("Please enter which plan\n"
            + "you are subscribed to (A, B, or C).");
        strUserHours = JOptionPane.showInputDialog("Please enter the total number\n"
            + "of hours you used this month.");
        
        // Convert hours to float value
        fltUserHours = Float.parseFloat(strUserHours);
        
        // Convert package selection to lower case for switch statement
        strUserPackage = strUserPackage.toLowerCase();
        
        // First switch statement. This one calculates only the relevant plans
        // for comparison with no breaks so the cases fall through. For example
        // if you get Package A, then A, B, and C will all be calculated for
        // comparison later. If you choose Package B, only B and C will be
        // calculated for comparison.
        switch(strUserPackage)
        {
            case "a": // Calculate cost for Package A
                if(fltUserHours <= FLT_PACKAGE_HOURS_A && fltUserHours > 0)
                {
                    fltCalculatedTotalA = FLT_PACKAGE_CHARGE_A;
                }
                else if(fltUserHours > 10)
                {
                    fltCalculatedTotalA = FLT_PACKAGE_OVERAGE_A * (fltUserHours
                            - FLT_PACKAGE_HOURS_A) + FLT_PACKAGE_CHARGE_A;
                }
                else if(fltUserHours <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a number\n"
                        + "of hours greater than 0.");
                    System.exit(0);
                }
            case "b": // Calculate cost for Package B
                if(fltUserHours <= FLT_PACKAGE_HOURS_B && fltUserHours > 0)
                {
                    fltCalculatedTotalB = FLT_PACKAGE_CHARGE_B;
                }
                else if(fltUserHours > 10)
                {
                    fltCalculatedTotalB = FLT_PACKAGE_OVERAGE_B * (fltUserHours
                            - FLT_PACKAGE_HOURS_B) + FLT_PACKAGE_CHARGE_B;
                }
                else if(fltUserHours <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a number\n"
                        + "of hours greater than 0.");
                    System.exit(0);
                }
            case "c": // Calculate cost for Package C
                fltCalculatedTotalC = FLT_PACKAGE_CHARGE_C;
                break;
            default:
                JOptionPane.showMessageDialog(null, "ERROR CALCULATING TOTAL");
                System.exit(0);
                break;
        }
        
        // This switch is complicated. It takes the calculated values and
        // and compares them to see which of strings that its formatting
        // should display. For example, if you chose Package A but the calculated
        // cost for Package B was cheaper, it will output a message letting
        // you know that you could have saved x dollars. If you chose package
        // A and both Packages B and C were cheaper, it will output a different
        // message. This is where the complexity of this switch comes from.
        switch(strUserPackage)
        {
            case "a":
                // If Package A cost is greater than Package B cost we still
                // need to test if Package A is also greater than package C
                // or less than Package C (tests false in range of 10-15ish)
                if(fltCalculatedTotalA > fltCalculatedTotalB)
                {
                    fltSavings1 = fltCalculatedTotalA - fltCalculatedTotalB;
                    fltSavings2 = fltCalculatedTotalA - fltCalculatedTotalC;
                    
                    // Convert back to uppercase for output to console
                    strUserPackage = strUserPackage.toUpperCase();
                    
                    // If Calculated Total Package A - Calculated Total Package C
                    // is greater than 0 dollars, then display the savings only
                    // for Calculated Total Package B
                    if(fltSavings2 >= 0)
                    {
                        // Format a string and numbers for output to the user
                        strFormattedOutput = String.format("Using %.1f hours on Package "
                            + strUserPackage
                            + " cost $%.2f.\nYou could have saved $%.2f\nby "
                            + "purchasing Package B\nand $%.2f by purchasing Package C",
                            fltUserHours,  fltCalculatedTotalA, fltSavings1,
                            fltSavings2);
                    }
                    // Else Calculated Total Package C is greater than 0 and
                    // should be displayed along with Calculated Total Package B
                    else
                    {
                        // Format a string and numbers for output to the user
                        strFormattedOutput = String.format("Using %.1f hours on Package "
                            + strUserPackage
                            + " cost $%.2f.\nYou could have saved $%.2f\nby "
                            + "purchasing Package B.",
                            fltUserHours,  fltCalculatedTotalA, fltSavings1);
                    }
                }
                // Else, if the above failed, then there were no cheaper packages
                // and this will output the original message telling the user
                // how much Package A (only) cost
                else
                {
                    // Convert back to uppercase for output to console
                    strUserPackage = strUserPackage.toUpperCase();

                    // Format a string and numbers for output to the user
                    strFormattedOutput = String.format("Using %.1f hours on Package "
                            + strUserPackage + " cost $%.2f.", fltUserHours,
                            fltCalculatedTotalA);
                }
                // Display formatted string to the user
                JOptionPane.showMessageDialog(null, strFormattedOutput);
                break;
            case "b": // Like above but simpler, only need to compare B to C
                if(fltCalculatedTotalB > fltCalculatedTotalC)
                { 
                    // Format a string and numbers for output to the user
                    strFormattedOutput = String.format("Using %.1f hours on Package "
                        + strUserPackage
                        + " cost $%.2f.\nYou could have saved $%.2f\nby "
                        + "purchasing Package C.", fltUserHours,
                        fltCalculatedTotalB, fltSavings1);
                }
                else
                {
                    // Format a string and numbers for output to the user
                    strFormattedOutput = String.format("Using %.1f hours on Package " +
                        strUserPackage
                        + " cost $%.2f.", fltUserHours, fltCalculatedTotalB);
                }
                // Display formatted string to the user
                JOptionPane.showMessageDialog(null, strFormattedOutput);
                break;
            // This case is special. In theory in the 10-15 hours of access range
            // Case A is cheaper than Case C but the prompt doesn't say to do
            // this so this case only outputs a single message.  ???
            case "c":
                // Convert back to uppercase for output to console
                strUserPackage = strUserPackage.toUpperCase();

                // Format a string and numbers for output to the user
                strFormattedOutput = String.format("Using %.1f hours on Package " +
                    strUserPackage
                    + " cost $%.2f.", fltUserHours, fltCalculatedTotalC);

                // Display formatted string to the user
                JOptionPane.showMessageDialog(null, strFormattedOutput);
                break;
            default:
                break;
        }
        
        // For JOptionPane
        System.exit(0);
    }
    
}

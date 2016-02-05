# Codon Count

An application to find out how many times each codon occurs in a strand of DNA based on reading frames. A strand of DNA is made up of the symbols C, G, T, and A. A codon is three consecutive symbols in a strand of DNA such as ATT or TCC. A reading frame is a way of dividing a strand of DNA into consecutive codons. Consider the following strand of DNA = "CGTTCAAGTTCAA".

There are three reading frames:

- The first reading frame starts at position 0 and has the codons: “CGT”, “TCA”, “AGT” and “TCA”. Here TCA occurs twice and the others each occur once.

- The second reading frame starts at position 1 (ignoring the first C character) and has the codons: “GTT”, “CAA”, “GTT”, “CAA”. Here both GTT and CAA occur twice.

- The third reading frame starts at position 2 (ignoring the first two characters CG) and has the codons: “TTC”, “AAG”, “TTC”. Here TTC occurs twice and AAG occurs once.

For example, for the string above and here: "CGTTCAAGTTCAA" also in "data/smalldna.txt", if we run our program and print the output requested above and specify to print codons and counts for those codons whose counts are between 1 and 5 inclusive, we might get the output:

Reading frame starting with 0 results in 3 unique codons

and most common codon is TCA with count 2

Counts of codons between 1 and 5 inclusive are:

CGT 1

TCA 2

AGT 1

Reading frame starting with 1 results in 2 unique codons

and most common codon is CAA with count 2

Counts of codons between 1 and 5 inclusive are:

CAA 2

GTT 2

Reading frame starting with 2 results in 2 unique codons

and most common codon is TTC with count 2

Counts of codons between 1 and 5 inclusive are:

TTC 2

AAG 1
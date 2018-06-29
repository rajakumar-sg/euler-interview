package com.rakalab.eulertriangle;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class BuilderTest {

    Builder builder = new Builder();

    @Test
    public void should_build_array_from_string() {
        assertArrayEquals(builder.buildArray("5"), new int[]{5});

        assertArrayEquals(builder.buildArray("5 20 15"), new int[]{5, 20, 15});

        assertArrayEquals(builder.buildArray("  5   2  20  7 "), new int[]{5, 2, 20, 7});
    }

    @Test
    public void should_build_triangle_of_max_sum_23() throws Exception {
        // given
        String input =
                " 3 \n" +
               " 7 4 \n" +
              " 2 4 6 \n" +
              "8 5 9 3 ";

        // when
        EulerTriangle eulerTriangle = builder.buildTriangle(input);

        // then
        assertThat(eulerTriangle.getMaximumSum(), is(23));
        assertThat(eulerTriangle.getHighestSumPath(), is("3 + 7 + 4 + 9"));
    }

    @Test
    public void should_build_triangle_of_max_sum_1074() throws Exception {
        // given
        String input =
                "                            75\n" +
                "                          95  64\n" +
                "                        17  47  82\n" +
                "                      18  35  87  10\n" +
                "                    20  04  82  47  65\n" +
                "                  19  01  23  75  03  34\n" +
                "                88  02  77  73  07  63  67\n" +
                "              99  65  04  28  06  16  70  92\n" +
                "            41  41  26  56  83  40  80  70  33\n" +
                "          41  48  72  33  47  32  37  16  94  29\n" +
                "        53  71  44  65  25  43  91  52  97  51  14\n" +
                "      70  11  33  28  77  73  17  78  39  68  17  57\n" +
                "    91  71  52  38  17  14  91  43  58  50  27  29  48\n" +
                "  63  66  04  68  89  53  67  30  73  16  69  87  40  31\n" +
                "04  62  98  27  23  09  70  98  73  93  38  53  60  04  23\n";

        // when
        EulerTriangle eulerTriangle = builder.buildTriangle(input);

        // then
        assertThat(eulerTriangle.getMaximumSum(), is(1074));
        assertThat(eulerTriangle.getHighestSumPath(), is("75 + 64 + 82 + 87 + 82 + 75 + 73 + 28 + 83 + 32 + 91 + 78 + 58 + 73 + 93"));
    }

    @Test
    public void should_build_triangle_of_max_sum_18() throws Exception {
        // given
        String input =
                "      1  \n" +
                "     2 3  \n" +
                "    6 5 4  \n" +
                "   7 8 9 1 ";

        // when
        EulerTriangle eulerTriangle = builder.buildTriangle(input);

        // then
        assertThat(eulerTriangle.getMaximumSum(), is(18));
        assertThat(eulerTriangle.getHighestSumPath(), is("1 + 3 + 5 + 9"));
    }
}
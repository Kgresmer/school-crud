package com.gresmer.school.crud.gresmerschoolcrud;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class TestConfig {


    public static void main(String[] args) {
        int[] i = new int[4];
        i[0] = 3;
        i[1] = 4;
        i[2] = 6;
        i[3] = 7;


        int i1 = searchInsert(i, 5);
        System.out.println(i1);
    }

    static int searchInsert(int[] nums, int target) {

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0] >= target ? 0 : 1;

        int mid = nums.length / 2;

        if (nums[mid] == target) return mid;

        int start, end;
        if (nums[mid] > target) {
            end = mid;
            start = 0;
        } else {
            end = nums.length -1;
            start = mid;
        }

        for (int i = start; i <= end; i++) {
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;

            if (nums[end] > target) {
                end--;
            }

            if (nums[start] < target) {
                start++;
            }
        }

        return start;
    }
}

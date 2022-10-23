package com.example.calculator.basic;

import static com.example.calculator.model.ResultBuilder.getResult;
import static com.example.calculator.model.ResultBuilder.getResultFromError;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculator.model.ApiResult;


@RestController
@RequestMapping("/basic")
public class BasicController {

   @GetMapping("/add")
   public ApiResult add(@RequestParam BigDecimal augend, @RequestParam BigDecimal addend ) {
      return getResult(augend.add(addend));
   }

   @GetMapping("/subtract")
   public ApiResult subtract(@RequestParam BigDecimal minuent, @RequestParam BigDecimal subtrahend) {
      return getResult(minuent.subtract(subtrahend));
   }

   @GetMapping("/multiply")
   public ApiResult multiply(@RequestParam BigDecimal multiplier, @RequestParam BigDecimal multiplicand) {
      return getResult(multiplier.multiply(multiplicand));
   }

   @GetMapping("/divide")
   public ApiResult divide(@RequestParam BigDecimal dividend, @RequestParam BigDecimal divisor) {
      if (divisor.equals(BigDecimal.ZERO)) {
         return getResultFromError("no division by null");
      }
      return getResult(dividend.divide(divisor, RoundingMode.HALF_EVEN));
   }
   

   @GetMapping("/shipping")
   public ApiResult shipping(@RequestParam double packageWeight, @RequestParam double packageHeight, @RequestParam double packageWidth,@RequestParam double packagLength) {
      double finalPrice = 0;

      double packagVolume = 0;
      
      //Computations

      packagVolume=	packageHeight * packageWidth * packagLength;
      if (packageWeight > 50) {
    	  finalPrice = 0;
      }else if (packageWeight >= 10)  {
    	  finalPrice = 20 * packageWeight;
      }else if (packagVolume <=1500)  {
    	  finalPrice = 0.03 *packagVolume;
      }else if (packagVolume >1500  &&  packagVolume <=2500)  {
    	  finalPrice = 0.04 *packagVolume;	  
      }else  {
    	  finalPrice = 0.05 *packagVolume;	 

      }
      
      
      return getResult(finalPrice);
      
   }

}

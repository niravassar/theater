package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var theaterService : TheaterService

    @RequestMapping("helloWorld")
    fun helloWorld(): ModelAndView {
        return ModelAndView("helloWorld")
    }

    @RequestMapping("/")
    fun homePage(): ModelAndView = ModelAndView("seatBooking", "bean", CheckAvailabilityBackBean())

    @RequestMapping("checkAvailability", method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackBean) : ModelAndView {
        val seat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val backBean = CheckAvailabilityBackBean()
        backBean.result = "Seat check is row: ${ bean.selectedSeatRow}, seat ${bean.selectedSeatNum}. It is available: $seat"
        return ModelAndView("seatBooking", "bean", backBean)
    }

}

class CheckAvailabilityBackBean() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum : Int = 1
    var selectedSeatRow : Char = 'A'
    var result : String = ""
}
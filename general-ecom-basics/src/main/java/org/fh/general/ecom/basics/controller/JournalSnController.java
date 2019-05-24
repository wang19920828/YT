package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.JournalSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-10-08
 */
@RestController
public class JournalSnController {

    @Autowired
    private JournalSnService journalSnService;

    @RequestMapping("JOU8001")
    public String getOrderJournalSn(){
        return this.journalSnService.orderJournalSn();
    }
	
}

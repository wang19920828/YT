package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pjj
 * @since 2018-10-08
 */
@Data
@TableName("tb_journal_sn")
public class JournalSn implements Serializable {

    private static final long serialVersionUID = 1L;

	private String name;
    /**
     * 当前编号
     */
	@TableField("current_value")
	private String currentValue;
    /**
     * 起始值
     */
	@TableField("start_num")
	private Long startNum;
    /**
     * 递增值
     */
	private Integer increment;
	private String prefix;
    /**
     * 描述
     */
	private String description;

}

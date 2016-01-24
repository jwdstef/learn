package org.zgf.learn.hibernate.validator;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public class BeanVO {

	@NotNull(message = "对象不能为空:可用于所有类型")
	private String notNull;

	@Max(value = 10, message = "最大为10：max 只能修饰int")
	private Integer max_10;

	@Min(value = 2, message = "最小为2： min 只能修饰int")
	private Long min_2;

	@DecimalMin(value = "20.0", message = "最小值为20.0：修饰浮点型")
	private Double decimalMin_20;

	@DecimalMax(value = "80.0", message = "最大值为80.0：修饰浮点型")
	private Double decimalMax_80;

	@Digits(integer = 3, fraction = 2, message = "整数最多3位，小数最多两位：只能修饰浮点型")
	private Double digits_3_2;

	@Past(message = "日期必须为过去时")
	private Date past;

	@Future(message = "日期必须是未来时")
	private Date future;

	@Size(min = 3, max = 5, message = "字符串长度3~5：只能修饰字符串")
	private String size_3_5;

	@Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号格式不正确:正则表达式只能修饰字符串")
	private String pattern_phoneno;

	/********* hibernate validator 新增 ***************/

	@NotEmpty(message = "字符串不能为空或空格：只能修饰字符串")
	private String notEmpty;

	@NotBlank(message = "字符串不能为空")
	private String notBlank;

	@Length(min = 4, max = 20, message = "字符串长度为4~20：只能修饰字符串")
	private String length_4_20;

	@Email(message = "用户邮箱格式不正确")
	private String email;

	@URL(message = "网址格式不正确")
	private String url;

	@Range(min = 1, max = 3, message = "range:")
	private String range_1_3;

	public BeanVO() {
		super();
	}

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

	public int getMax_10() {
		return max_10;
	}

	public void setMax_10(Integer max_10) {
		this.max_10 = max_10;
	}

	public Long getMin_2() {
		return min_2;
	}

	public void setMin_2(Long min_2) {
		this.min_2 = min_2;
	}

	public Double getDecimalMin_20() {
		return decimalMin_20;
	}

	public void setDecimalMin_20(Double decimalMin_20) {
		this.decimalMin_20 = decimalMin_20;
	}

	public Double getDecimalMax_80() {
		return decimalMax_80;
	}

	public void setDecimalMax_80(Double decimalMax_80) {
		this.decimalMax_80 = decimalMax_80;
	}

	public double getDigits_3_2() {
		return digits_3_2;
	}

	public void setDigits_3_2(Double digits_3_2) {
		this.digits_3_2 = digits_3_2;
	}

	public Date getPast() {
		return past;
	}

	public void setPast(Date past) {
		this.past = past;
	}

	public Date getFuture() {
		return future;
	}

	public void setFuture(Date future) {
		this.future = future;
	}

	public void setRange_1_3(String range_1_3) {
		this.range_1_3 = range_1_3;
	}

	public String getSize_3_5() {
		return size_3_5;
	}

	public void setSize_3_5(String size_3_5) {
		this.size_3_5 = size_3_5;
	}

	public String getPattern_phoneno() {
		return pattern_phoneno;
	}

	public void setPattern_phoneno(String pattern_phoneno) {
		this.pattern_phoneno = pattern_phoneno;
	}

	public String getNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(String notEmpty) {
		this.notEmpty = notEmpty;
	}

	public String getNotBlank() {
		return notBlank;
	}

	public void setNotBlank(String notBlank) {
		this.notBlank = notBlank;
	}

	public String getLength_4_20() {
		return length_4_20;
	}

	public void setLength_4_20(String length_4_20) {
		this.length_4_20 = length_4_20;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRange_1_3() {
		return range_1_3;
	}

	public void setRange(String range_1_3) {
		this.range_1_3 = range_1_3;
	}

}

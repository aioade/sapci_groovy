import com.sap.it.api.mapping.*;

def String getCalendarDetails(String dateStr, String type){
	
	//create Calendar object
	def date = Date.parse('yyyy-MM-dd', dateStr)
	def calendar = Calendar.getInstance()
    calendar.setTime(date)
    
    if (type.toLowerCase() == 'week') {
        return calendar.get(Calendar.WEEK_OF_YEAR)
    } else if (type.toLowerCase() == 'quarter') {
        def month = calendar.get(Calendar.MONTH)
        
        //calculate quarter
        return (month / 3).intValue() + 1
    } else {
        throw new IllegalArgumentException("Type muss entweder 'week' oder 'quarter' sein")
    }
}
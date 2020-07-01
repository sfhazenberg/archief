#Brief Python script which copies files based on Excel input

print("About to copy files")

def main():
    import os
    import shutil
    import openpyxl
    from pathlib import Path
    
    wb = openpyxl.load_workbook("path to excel file")
    sheet = wb.get_sheet_by_name("Sheet1")
    dst = "path to destination folder"
    maxRows = sheet.max_row
    i = 1
    
    while i < (maxRows + 1):
        tempFile = sheet["A" + str(i)].value
        if os.path.isfile(tempFile):     #if file exists
            shutil.copy2(tempFile, dst)  #copy it to destination
            print("File " + str(tempFile) + "succesfully copied.")
            i += 1
        else:                            #if file is not found, then skip
            print("! File " + str(tempFile) + " not found.")
            i += 1
            with open('missingFiles.txt','a') as logFile:   #and write to log
                logFile.write(str(tempFile) + " not found.\n")
        
    print("Copying files complete. Missing files logged to missingFiles.txt.")
    input("Press ENTER to exit.")

main()

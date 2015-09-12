solve <- function() {

    limit <- 1000
    multiples <- c(3, 5)
    result <- 0

    for (i in min(multiples):limit-1) {
        for (j in multiples) {
            if (i %% j == 0) {
                result <- result + i
                break
            }
        }
    }

    return(result)
}

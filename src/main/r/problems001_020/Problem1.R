solve <- function() {
    limit <- 1000
    multiples <- c(3, 5)
    values <- rep(seq(min(multiples), limit-1), length(multiples))
    return(sum(unique(values[(values %% sort(rep(multiples, length(values)/2))) == 0])))
}
